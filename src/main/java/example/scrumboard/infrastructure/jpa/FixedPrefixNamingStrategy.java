package example.scrumboard.infrastructure.jpa;

import org.hibernate.cfg.ImprovedNamingStrategy;

import com.google.common.base.Strings;

public class FixedPrefixNamingStrategy extends ImprovedNamingStrategy {

	private static final long serialVersionUID = 1L;

	private static final String TABLE_PREFIX = "t_";

	private static final String COLUMN_PREFIX = "c_";

	@Override
	public String classToTableName(String className) {
		return TABLE_PREFIX + super.classToTableName(className);
	}

	@Override
	public String collectionTableName(String ownerEntity, String ownerEntityTable, String associatedEntity,
			String associatedEntityTable, String propertyName) {
		if (!Strings.isNullOrEmpty(associatedEntity) && !Strings.isNullOrEmpty(associatedEntityTable)) {
			return TABLE_PREFIX + tableName(ownerEntityTable) + "_" + super.tableName(associatedEntityTable);
		} else {
			return TABLE_PREFIX + tableName(ownerEntityTable) + "_" + super.propertyToColumnName(propertyName);
		}
	}

	@Override
	public String propertyToColumnName(String propertyName) {
		return COLUMN_PREFIX + super.propertyToColumnName(propertyName);
	}

	@Override
	public String joinKeyColumnName(String joinedColumn, String joinedTable) {
		return COLUMN_PREFIX + super.joinKeyColumnName(joinedColumn, joinedTable);
	}

	@Override
	public String columnName(String columnName) {
		return COLUMN_PREFIX + super.columnName(columnName);
	}
}