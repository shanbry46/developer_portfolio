const Constants = {

    get ApiEndpoint(): string {
        //return "https://localhost:5001";
        return "https://accutreatapi.azurewebsites.net"
    },

    get RxNormEndpoint(): string {
        return  "https://rxnav.nlm.nih.gov/REST";
    }

}

export default Constants
