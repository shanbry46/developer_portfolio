﻿// <auto-generated />
using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;
using Npgsql.EntityFrameworkCore.PostgreSQL.Metadata;
using accutreat_api.Models;

namespace accutreat_api.Migrations
{
    [DbContext(typeof(AccutreatDbContext))]
    partial class AccutreatDbContextModelSnapshot : ModelSnapshot
    {
        protected override void BuildModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn)
                .HasAnnotation("ProductVersion", "3.1.9")
                .HasAnnotation("Relational:MaxIdentifierLength", 63);

            modelBuilder.Entity("accutreat_api.Models.DrugPrice", b =>
                {
                    b.Property<int>("dp_id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("integer")
                        .HasAnnotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn);

                    b.Property<string>("business")
                        .HasColumnType("text");

                    b.Property<DateTime>("date")
                        .HasColumnType("timestamp without time zone");

                    b.Property<string>("drug_name")
                        .IsRequired()
                        .HasColumnType("character varying(50)")
                        .HasMaxLength(50);

                    b.Property<double>("price")
                        .HasColumnType("double precision");

                    b.Property<string>("rxcui")
                        .HasColumnType("text");

                    b.Property<int>("zip_code")
                        .HasColumnType("integer");

                    b.Property<int>("quantity")
                        .HasColumnType("integer");

                    b.HasKey("dp_id");

                    b.ToTable("drugprices");
                });

            modelBuilder.Entity("accutreat_api.Models.User", b =>
                {
                    b.Property<string>("email")
                        .HasColumnType("text");

                    b.Property<string>("password_hash")
                        .IsRequired()
                        .HasColumnType("text");

                    b.Property<string>("user_name")
                        .IsRequired()
                        .HasColumnType("text");

                    b.HasKey("email");

                    b.ToTable("users");
                });


            modelBuilder.Entity("accutreat_api.Models.DrugComment", b =>
                {
                    b.Property<string>("drug_name")
                        .IsRequired()
                        .HasColumnType("text");

                    b.Property<string>("drug_comments")
                        .IsRequired()
                        .HasColumnType("text");

                    b.Property<string>("dc_id")
                        .IsRequired()
                        .HasColumnType("integer");

                    b.HasKey("dc_id");

                    b.ToTable("drug_comments");
                });
#pragma warning restore 612, 618
        }
    }
}