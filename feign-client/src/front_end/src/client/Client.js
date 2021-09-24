import fetch from 'unfetch';

const checkStatus = response => {
    if (response.ok) {
        return response;
    }
    // convert non-2xx HTTP responses into errors:
    const error = new Error(response.statusText);
    error.response = response;
    return Promise.reject(error);
}

export const getAllCustomers = () =>
    fetch("/customer")
        .then(checkStatus);

export const getCustomer = (nationalId) =>
    fetch("/customer/"+nationalId)
            .then(checkStatus);

export const addNewCustomer = customer =>
    fetch("customer", {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify(customer)
        }
    ).then(checkStatus)


export const updateCustomer = (nationalId,customer) =>
    fetch("customer/"+nationalId, {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'PUT',
            body: JSON.stringify(customer)
        }
    ).then(checkStatus)


export const deleteCustomer = nationalId =>
    fetch( "customer/"+nationalId, {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'DELETE',
    }).then(checkStatus);

export const creditRequestNewCustomer = customer =>
    fetch("score/requestCreditNewCustomer", {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify(customer)
    }
    ).then(checkStatus)

export const creditRequestNationalId = nationalId =>
    fetch("score/requestCreditByNationalId/"+nationalId, {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'POST'
    }
    ).then(checkStatus)


export const getRequestByNationalId = nationalId =>
    fetch("score/getRequestByNationalId/"+nationalId, {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'POST'
    }
    ).then(checkStatus)

