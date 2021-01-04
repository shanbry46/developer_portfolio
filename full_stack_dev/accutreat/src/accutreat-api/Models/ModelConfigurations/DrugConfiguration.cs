using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace accutreat_api.Models
{
    public class DrugConfiguration : IEntityTypeConfiguration<DrugPrice>
    {
        public DrugConfiguration(){}
        public void Configure(EntityTypeBuilder<DrugPrice> builder)
        {
            builder.Property(prop => prop.drug_name)
            .HasMaxLength(50);
        }
    }
}