package QueryBuilder;

public class QueryBuilder {
    private StringBuilder query;
    private boolean hasSelect;
    private boolean hasFrom;
    private boolean hasWhere;

    private QueryBuilder() {
        this.query = new StringBuilder();
        this.hasSelect = false;
        this.hasFrom = false;
        this.hasWhere = false;
    }

    public static QueryBuilder select(String... columns) {
        QueryBuilder builder = new QueryBuilder();
        builder.query.append("SELECT ");
        
        if (columns.length == 0) {
            builder.query.append("*");
        } else {
            builder.query.append(String.join(", ", columns));
        }
        
        builder.hasSelect = true;
        return builder;
    }

    public QueryBuilder from(String table) {
        if (!hasSelect) {
            throw new IllegalStateException("SELECT must be called before FROM");
        }
        
        query.append(" FROM ").append(table);
        hasFrom = true;
        return this;
    }

    public QueryBuilder where(String condition) {
        if (!hasFrom) {
            throw new IllegalStateException("FROM must be called before WHERE");
        }
        
        query.append(" WHERE ").append(condition);
        hasWhere = true;
        return this;
    }

    public QueryBuilder and(String condition) {
        if (!hasWhere) {
            throw new IllegalStateException("WHERE must be called before AND");
        }
        
        query.append(" AND ").append(condition);
        return this;
    }

    public QueryBuilder or(String condition) {
        if (!hasWhere) {
            throw new IllegalStateException("WHERE must be called before OR");
        }
        
        query.append(" OR ").append(condition);
        return this;
    }

    public QueryBuilder orderBy(String column) {
        if (!hasFrom) {
            throw new IllegalStateException("FROM must be called before ORDER BY");
        }
        
        query.append(" ORDER BY ").append(column);
        return this;
    }

    public QueryBuilder limit(int count) {
        if (!hasFrom) {
            throw new IllegalStateException("FROM must be called before LIMIT");
        }
        
        query.append(" LIMIT ").append(count);
        return this;
    }

    public String build() {
        if (!hasSelect) {
            throw new IllegalStateException("SELECT clause is required");
        }
        if (!hasFrom) {
            throw new IllegalStateException("FROM clause is required");
        }
        
        return query.toString() + ";";
    }
}