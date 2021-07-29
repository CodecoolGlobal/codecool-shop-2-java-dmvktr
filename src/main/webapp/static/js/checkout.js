
//function initCheckoutButton() {
    //let checkoutButton = document.getElementById('checkout-button');
    //checkoutButton.addEventListener("click", createUrlForPayment)
//}

function initCheckboxes() {
    let selectedPaymentMethod = "";
    let paymentCheckBoxes = document.getElementsByClassName("payment-custom-control-input");
    for (let checkBox of paymentCheckBoxes) {
        checkBox.addEventListener("click", createUrlForPayment)
        console.log(selectedPaymentMethod);
    }
}

function createUrlForPayment() {
        let selectedPayment = ""
        let paymentCheckBoxes = document.getElementsByClassName("payment-custom-control-input");
        for (let checkbox of paymentCheckBoxes) {
            if (checkbox.checked) {
                selectedPayment = this.id;
            }
        }
        if (selectedPayment !== "") {
            let checkoutButton = document.getElementById('checkout-button');
            checkoutButton.href = "/" + selectedPayment + "_payment";
        }
}

initCheckboxes();