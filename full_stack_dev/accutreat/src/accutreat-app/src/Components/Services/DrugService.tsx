import Constants from "../../Constants";
import Axios, { AxiosResponse } from "axios";
import IDrugPrice from "../../Models/IDrugPrice";
import IDrugComment from "../../Models/IDrugComment";

export const searchDrug = async (drugName: string, zipCode?: string) => {
    return (!!zipCode) ? Axios.get(Constants.ApiEndpoint + "/drug/" + drugName + "/price?zip_code=" + zipCode)
        .then((resp) => resp.data)
        .catch((e) => []) :
        Axios.get(Constants.ApiEndpoint + "/drug/" + drugName + "/price")
            .then((resp) => resp.data)
            .catch((e) => [])
}

export const createDrugPrice = async (drugToCreate: IDrugPrice): Promise<AxiosResponse<any>> => {
    return Axios.post(`${Constants.ApiEndpoint}/drug`, drugToCreate);
}

export const createDrugComment = async (commentToCreate: IDrugComment): Promise<AxiosResponse<any>> => {
    return Axios.post(`${Constants.ApiEndpoint}/comment`, commentToCreate);
}

export const searchComments = async (drugName: string) => {
    if(drugName.includes("/")) {
        drugName = drugName.substring(0,drugName.indexOf('/')).trim();
    }
    var uri= Constants.ApiEndpoint + "/comment/" + encodeURIComponent(drugName);
    return (
        Axios.get(uri)
        .then((resp) => resp.data)
        .catch((e) => []))
}