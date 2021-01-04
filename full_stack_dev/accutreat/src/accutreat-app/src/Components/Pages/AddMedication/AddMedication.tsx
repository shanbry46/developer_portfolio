import React, { useState } from "react";
import IDrugPrice from "../../../Models/IDrugPrice";
import IDrugComment from "../../../Models/IDrugComment";
import "./AddMedication.css";
import { createDrugComment, createDrugPrice } from "../../Services/DrugService";
import MedicationSuggestionBar from "./MedicationSuggestionBar";
import { getDrugNameSuggestions, getDrugs } from "../../Services/RxNormService";
import { IDrugDropdownSuggestion, IDrugSuggestion } from "../../../Models/IDrugSuggestion";
import Select, { ActionMeta, ValueType } from "react-select";
import {useHistory} from 'react-router-dom';
import StateManager from "react-select";

interface IState {
  drugName: string;
  price: number;
  quantity: string;
  zipCode: number;
  rxcui: number;
  comments: string;
  drugSuggestions: IDrugDropdownSuggestion[];
  hasSelectedSpecificDrug: boolean
}

const initialState: IState = {
  drugName: "",
  price: 0.0,
  quantity: "",
  zipCode: 0,
  rxcui: 0,
  comments: "",
  drugSuggestions: [],
  hasSelectedSpecificDrug: false
};

const AddMedication: React.FunctionComponent = () => {
  const [state, setState] = useState(initialState);
  const history = useHistory();

  const handleAddMedication = async () => {

    if (state.comments.trim() != "") {
       createNewDrugComment();
    }
    if ((state.price) && (state.quantity)) {
       createNewDrugPrice();
    }
    history.push(`/`);
    alert("Your medication information has been submitted.");
    setState(initialState);
 }

  const createNewDrugPrice = async () => {
    let drugToCreate: IDrugPrice = {
      drug_name: state.drugName,
      price: state.price,
      quantity: state.quantity,
      rxcui: state.rxcui,
      zip_code: state.zipCode,
    };

    let result = await createDrugPrice(drugToCreate);
  };

  const createNewDrugComment = async () => {
    let commentToCreate: IDrugComment = {
       drug_name: state.drugName,
       drug_comments: state.comments
    };
    let result = await createDrugComment(commentToCreate);
  }

  const onSelectChange = (newValue: any): void => {
      
      let parsedRxcui: number = newValue && newValue.value && parseInt(newValue.value);
      setState({
         ...state,
         rxcui: parsedRxcui,
         drugName: newValue && newValue.label});
  }

  return (
    <div className="wrapper">
      <div className="form-wrapper" id="add-med-fw">
        <h2>Add Medication</h2>
        <MedicationSuggestionBar
          onSelectionChosen={ async (value: string) => {
            let result = await getDrugs(value);
            setState({
               ...state,
               drugSuggestions: result
            })
          }}
          onSuggestionsFetchRequested={(s: string) => {
            return getDrugNameSuggestions(s).then(
              (result: IDrugSuggestion) => result.suggestionList
            );
          }}
        />
        <div className="drug">
          <label htmlFor="drug">Drug</label>
          <Select required id="drugSuggestions" options={state.drugSuggestions} onChange={onSelectChange}/>
        </div>
        <div className="price">
          <label htmlFor="price">Price Paid </label>
          <input
            required
            type="number"
            name="price"
            id = "price"
            onBlur={(e) =>
              setState({ ...state, price: +e.currentTarget.value })
            }
          />
        </div>
        <div className="quantity">
          <label htmlFor="quantity">Quantity </label>
          <input
            required
            type="number"
            name="quantity"
            id ="quantity"
            min="1"
            step="1"
            onBlur={(e) =>
              setState({ ...state, quantity: e.currentTarget.value})
            }
          />
        </div>
        <div className="zipcode">
          <label htmlFor="zipcode">Zip Code </label>
          <input
            required
            type="number"
            name="zipcode"
            id="zipcode"
            onBlur={(e) =>
              setState({ ...state, zipCode: +e.currentTarget.value })
            }
          />
        </div>
        <div className="comments">
          <label htmlFor="comments">Comments </label>
          <textarea
            name="comments"
            id="comments"
            onBlur={(e) =>
              setState({ ...state, comments: e.currentTarget.value })
            }
          />
        </div>
        <div className="submit">
          <button className="submit-btn" onClick={handleAddMedication}>
            Submit Medication
          </button>
        </div>
      </div>
    </div>
  );
};

export default AddMedication;
