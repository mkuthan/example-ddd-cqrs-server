package example.scrumboard.rest.queries.product

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import example.scrumboard.domain.product.ProductId
import example.scrumboard.rest.queries.product.dtos.ProductBacklogItemDto
import example.scrumboard.rest.queries.product.dtos.ProductDto
import groovy.sql.Sql


@RestController
class ProductQueryController {

	@Autowired
	Sql sql

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	def products(Pageable page) {
		new PageImpl(productsContent(page), page, productsCount())
	}

	@RequestMapping(value = "/products/{productId}", method = RequestMethod.GET)
	def product(@PathVariable("productId") ProductId productId) {
		def query = """
			SELECT 
				p.c_product_id, 
				p.c_name, 
				COUNT(pbi.c_product_id) count
			FROM 
				t_product p
			LEFT OUTER JOIN t_product_backlog_item pbi ON 
				p.c_product_id = pbi.c_product_id 
			WHERE 
				p.c_product_id = ?
			GROUP BY 
				p.c_product_id, p.c_name
		"""

		sql.rows(query, [productId.id], 0, 1).collect { row ->
			new ProductDto(
					productId: row.c_product_id,
					productName: row.c_name,
					backlogItemsCount: row.count
					)
		}.first()
	}

	@RequestMapping(value = "/products/{productId}/backlogItems", method = RequestMethod.GET)
	def backlogItems(@PathVariable("productId") ProductId productId) {
		def query = """
			SELECT 
				bi.c_backlog_item_id, 
				bi.c_story, 
				pbi.c_position
			FROM 
				t_product_backlog_item pbi
			RIGHT OUTER JOIN t_product p ON
				pbi.c_product_id = p.c_product_id
			RIGHT OUTER JOIN t_backlog_item bi ON
				pbi.c_backlog_item_id = bi.c_backlog_item_id
			WHERE 
				p.c_product_id = ?
				ORDER BY pbi.c_position
		"""

		sql.rows(query, [productId.id]).collect { row ->
			new ProductBacklogItemDto(
					backlogItemId: row.c_backlog_item_id,
					backlogItemStory: row.c_story,
					backlogItemPosition: row.c_position
					)
		}
	}

	private List<ProductDto> productsContent(Pageable page) {
		def query = """
			SELECT 
				p.c_product_id, 
				p.c_name, 
				COUNT(pbi.c_product_id) count
			FROM
				t_product p
			LEFT OUTER JOIN t_product_backlog_item pbi ON 
				p.c_product_id = pbi.c_product_id
			GROUP BY 
				p.c_product_id, 
				p.c_name
			ORDER BY
				p.c_name
		"""

		sql.rows(query, page.offset, page.pageSize).collect { row ->
			new ProductDto(
					productId: row.c_product_id,
					productName: row.c_name,
					backlogItemsCount: row.count
					)
		}
	}

	private Long productsCount() {
		def query = """
			SELECT
				count(*) count 
			FROM 
				t_product
		"""

		sql.firstRow(query).count
	}
}