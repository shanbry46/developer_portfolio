## DB Information

Made a PostgreSQL DB in Azure at [here](https://portal.azure.com/#@gtvault.onmicrosoft.com/resource/subscriptions/af046876-ad9c-424f-9226-360b00bd9b30/resourceGroups/6440/providers/Microsoft.DBforPostgreSQL/servers/accutreatdb/overview)

You can use any of the following tools to easily connect to the DB:
* [pgAdmin](https://www.pgadmin.org/)
* [Azure Data Studio](https://docs.microsoft.com/en-us/sql/azure-data-studio/download-azure-data-studio?view=sql-server-ver15) (My personal pick)
* [JetBrains DataGrip](https://www.jetbrains.com/datagrip/?&msclkid=fff24816b9291e86645a176b695c2eef&utm_source=bing&utm_medium=cpc&utm_campaign=AMER_en_US-EST_DataGrip_Branded&utm_term=%2B%22datagrip%22&utm_content=%2B%22datagrip%22&gclid=CLTUm7TYouwCFQSMswodd0wA8A&gclsrc=ds) (this really only allows for a 30 day trial so I would personally go with one of the first two since they are free. I like Azure Data Studio because it looks more modern and is a dedicated app vs pgAdmin runs on localhost in a browser)

I created the tables based on the following EER from @jspringer30 and our discussions in the syncs.

So I have it in Azure, schema based on our discussions (but it can be changed with team review), and single instance.

I added passwords to users as a column and made the locations based on zip code.