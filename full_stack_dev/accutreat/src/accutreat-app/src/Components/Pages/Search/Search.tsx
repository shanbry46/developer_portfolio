import React from 'react';
import './Search.css';
import 'react-bootstrap-table2-paginator/dist/react-bootstrap-table2-paginator.min.css';
import 'react-bootstrap-table-next/dist/react-bootstrap-table2.min.css';
import DataTable from './DataTable';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'react-bootstrap';
import IDrugPrice from '../../../Models/IDrugPrice'
import { searchDrug } from '../../Services/DrugService'

interface IState {
    drugPricesToPopulate: IDrugPrice[];
    searchDrugVal: string;
}

class Search extends React.Component<{}, IState> {
    constructor(props: any) {
        super(props);
        this.state = {
            drugPricesToPopulate: [],
            searchDrugVal: window.location.search.substring(window.location.search.indexOf('=')+1)
        }
        this.setValues = this.setValues.bind(this);
    }

    componentDidMount() {
        this.setValues();
    }

    setValues() {
        if(this.state.searchDrugVal) {
            this.handleSearchClick();
        }
    }

    public render() {
        return (
            <div className='wrapper'>
                <div className='form-wrapper' id='search-fw' onLoad= {() => this.setValues()}>
                    <h2>Search Fair Price</h2>
                    <div className='searchbyname'>
                        <input 
                        type="text" 
                        placeholder="Drug Name" 
                        name="search" 
                        id="drugName" 
                        value={this.state.searchDrugVal} 
                        onChange= {(e) => 
                            this.setState({ ...this.state, searchDrugVal: e.currentTarget.value})
                        }
                        />
                    </div>
                    <br></br>
                    <div className='searchbyzip'>
                        <input type="text" placeholder="Zip Code" name="search" id="zipCode" />
                    </div>
                    <div className='submit'>
                        <button className='submit-btn' type='submit' onClick={this.handleSearchClick}>
                            Search
                        </button>
                    </div>
                    <br></br>
                    <div id='expanded-container'>
                        <DataTable meds={this.state.drugPricesToPopulate} />
                    </div>
                </div>
            </div>
        );
    }

    private handleSearchClick = async () => {
        var drugToSearch = ((document.getElementById("drugName") as HTMLInputElement).value);
        var zipCode = ((document.getElementById("zipCode") as HTMLInputElement).value);
        if (drugToSearch === "") {
            alert("Please enter a drug name to search.");
        }
        else if (drugToSearch === "") {
            alert("Cannot search on an empty drug. Please provide a drug name.")
        }
        else if (zipCode === "") {
            let result = await searchDrug(drugToSearch);
            if(result.length == 0) {
                alert("There were no results returned for your search.");
            }
            else {
                this.setState({ drugPricesToPopulate: result});
            }
        }
        else {
            let result = await searchDrug(drugToSearch, zipCode);
            if(result.length == 0) {
                alert("There were no results returned for your search.");
            }
            else {
                this.setState({ drugPricesToPopulate: result});
            }
        }
    }
}

export default Search