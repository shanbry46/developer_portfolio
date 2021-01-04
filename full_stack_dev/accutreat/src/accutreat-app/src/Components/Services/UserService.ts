import Constants from "../../Constants";
import Axios, { AxiosResponse, AxiosError } from "axios";
import { IUser } from "../../Models/IUser";

interface IUserToAdd {
    user_name: string,
    email: string;
    password_hash: string;
    zip_code: number
}

export const attemptLogin = (email: string, passwordHash: string): Promise<IUser | void> => {
    return Axios.get<IUser>(`${Constants.ApiEndpoint}/user/login?email=${email}&hash=${passwordHash}`)
        .then(response => {
            let data: any = response.data;
            return {
                email: data.email,
                userName: data.user_name,
                zip: data.zip_code,
            }
        })
        .catch(error => console.log(error));
}

export const doesUserExist = async (email: string) => {
    return Axios.get(Constants.ApiEndpoint + '/user/' + email)
        .then((resp: AxiosResponse) => {
            return true;
        })
        .catch((error: AxiosError) => {
            if (error.response) {
                return false;
            }
        })
}

export const createUser = async (userToAdd: IUserToAdd) => {
    return Axios.post<IUser>(Constants.ApiEndpoint + "/user", userToAdd)
        .then(response => {
            let data: any = response.data;
            return {
                email: data.email,
                userName: data.user_name,
                zip: data.zip_code,
            }
        })
        .catch(error => console.log(error));
}