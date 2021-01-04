import Axios from "axios";

import Constants from "../../Constants";
import { IDrugSuggestion, IDrugDropdownSuggestion } from "../../Models/IDrugSuggestion";

export const getDrugNameSuggestions = (s: string): Promise<IDrugSuggestion> => {
  return Axios.get(
    `${Constants.RxNormEndpoint}/spellingsuggestions?name=${s}`
  ).then((response) => {
    let suggestionResponse: any = response.data;

    return {
      name: suggestionResponse.name,
      suggestionList:
        suggestionResponse.suggestionGroup.suggestionList.suggestion,
    };
  });
};

export const getDrugs = (drugName: string): Promise<IDrugDropdownSuggestion[]> => {
  return Axios.get(`${Constants.RxNormEndpoint}/drugs?name=${drugName}`).then(
    (response: any) => {
      let data = response.data.drugGroup.conceptGroup;

      let result: IDrugDropdownSuggestion[] = []

      data.forEach( (element: any) => {
          if (element.conceptProperties) {
              element.conceptProperties.map( (conceptProperty: any) => {
                  result.push({
                    label: conceptProperty.name,
                    value: conceptProperty.rxcui,
                  })
              })
          }
      });

      return result;
    }
  );
};
