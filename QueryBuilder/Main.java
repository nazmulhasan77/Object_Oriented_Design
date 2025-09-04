package QueryBuilder;
public class Main {
    public static void main(String[] args) {
        // Example 1
        String query1 = QueryBuilder.select("id", "name", "email")
            .from("users")
            .where("age > 18")
            .and("status = 'active'")
            .orderBy("name")
            .limit(10)
            .build();
        System.out.println(query1);
        
        // Example 2
        QueryBuilder qb = QueryBuilder
            .select("name", "email")
            .from("employees")
            .where("department = 'IT'")
            .or("department = 'HR'")
            .orderBy("name")
            .limit(5);
            
        String sql = qb.build();
        System.out.println(sql);
    }
}