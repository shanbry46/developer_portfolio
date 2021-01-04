import React, { useState } from 'react';
import BootstrapTable from 'react-bootstrap-table-next';
import paginationFactory from 'react-bootstrap-table2-paginator';
import IDrugPrice from '../../../Models/IDrugPrice';
import { Modal, Button } from "react-bootstrap"
import './Search.css';
import { searchComments } from '../../Services/DrugService';

interface IProps {
    meds: IDrugPrice[]
}

const DataTable: React.FunctionComponent<IProps> = (props) => {

    const drug_columns = [
        {dataField: 'drug_name', text: 'Name'},
        {dataField: 'zip_code', text: 'Location'},
        {dataField: 'quantity', text: 'Quantity'},
        {dataField: 'price', text: 'Fair Price'},
        {dataField: 'rxcui', text: 'Drug ID'},
    ];

    const comment_columns = [
        {dataField: 'drug_name', text: 'Name'},
        {dataField: 'drug_comments', text: 'Comment'},
        {dataField: 'price', text: 'Fair Price'},
    ];

    let drugComments: []

    const [comments, setComments] = useState([]);
    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);


    const rowEvents = {
        onClick: async (e: any,row: any) => {
            console.log(row.drug_name);
            const comment_result =  await searchComments(row.drug_name)
            drugComments = comment_result.map(function(el: any) {
                var o = Object.assign({}, el);
                o.price = row.price;
                return o;
              })
            console.log(drugComments);
            setComments(drugComments)
            toggleTrueFalse()
        },
    };

    const toggleTrueFalse = () => {
        setShow(true);
    };

    const ModalContent = () => {

        return (
            <Modal show={show} onHide={handleClose}>
                <Modal.Body>
                    <h5>Drug Comments</h5>
                    <BootstrapTable
                    keyField="drug_comments"
                    data={comments}
                    columns={comment_columns}
                    pagination={paginationFactory({})}
                />
                </Modal.Body>
                <Modal.Footer>
                 <Button variant="secondary" onClick={handleClose}>
                     Close
                 </Button>
                </Modal.Footer>
            </Modal>
        )
    }
    
    const [q, setQ] = useState('');

    function filter(rows: any[]) {
        return rows.filter((row: { drug_name: string; }) => row.drug_name.toLowerCase().indexOf(q) > -1);
    }

    return (
        <div>
            <div>
            <input
                id='filter-box'
                type='text'
                placeholder='Filter'
                style={{float: 'right'}}
                value={q}
                onChange={(e) => setQ(e.target.value)} />
            </div>
            <div>
                <BootstrapTable
                    bootstrap4
                    id='medication'
                    keyField="id"
                    data={filter(props.meds)}
                    columns={drug_columns}
                    search
                    hover
                    pagination={paginationFactory({})}
                    rowEvents={rowEvents}
                />
                {show ? <ModalContent/> : null}
            </div>
        </div>
    )   
}


export default DataTable;
