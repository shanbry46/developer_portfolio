using Microsoft.EntityFrameworkCore;

namespace accutreat_api.Models {
    public class AccutreatDbContext : DbContext {

        public AccutreatDbContext(DbContextOptions<AccutreatDbContext> options) :base(options)
        {
 
        }
        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.ApplyConfiguration(new DrugConfiguration());
        }

        public DbSet<DrugPrice> drug_price { get; set; }
        public DbSet<User> users { get; set; }
        public DbSet<DrugComment> drug_comments { get; set; }
    }

}
