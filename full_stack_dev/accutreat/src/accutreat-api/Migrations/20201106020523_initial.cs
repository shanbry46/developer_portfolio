using System;
using Microsoft.EntityFrameworkCore.Migrations;
using Npgsql.EntityFrameworkCore.PostgreSQL.Metadata;

namespace accutreat_api.Migrations
{
    public partial class initial : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "drugprices",
                columns: table => new
                {
                    dp_id = table.Column<int>(nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn),
                    drug_name = table.Column<string>(maxLength: 50, nullable: false),
                    zip_code = table.Column<int>(nullable: false),
                    price = table.Column<double>(nullable: false),
                    date = table.Column<DateTime>(nullable: false),
                    business = table.Column<string>(nullable: true),
                    rxcui = table.Column<string>(nullable: true),
                    quantity = table.Column<int>(nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_drugprices", x => x.dp_id);
                });

            migrationBuilder.CreateTable(
                name: "users",
                columns: table => new
                {
                    email = table.Column<string>(nullable: false),
                    user_name = table.Column<string>(nullable: false),
                    password_hash = table.Column<string>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_users", x => x.email);
                });
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "drugprices");

            migrationBuilder.DropTable(
                name: "users");
        }
    }
}
