function toggleAllCheckboxes(source) {
           const checkboxes = document.querySelectorAll('.item-checkbox');
           checkboxes.forEach(checkbox => checkbox.checked = source.checked);
           updateOrderSummary();
       }

       function updateOrderSummary() {
           const checkboxes = document.querySelectorAll('.item-checkbox');
           let totalAmount = 0;
           let itemCount = 0;
           checkboxes.forEach(checkbox => {
               if (checkbox.checked) {
                   const itemPrice = parseFloat(checkbox.getAttribute('data-price'));
                   const itemQuantity = parseInt(checkbox.getAttribute('data-quantity'));
                   totalAmount += itemPrice * itemQuantity;
                   itemCount++;
               }
           });

           document.getElementById('totalAmount').textContent = totalAmount.toLocaleString() + '원';
           document.getElementById('orderButton').textContent = itemCount + ' 건 주문하기';
       }

       function submitForm(action) {
           const checkboxes = document.querySelectorAll('.item-checkbox');
           let hasChecked = false;
           checkboxes.forEach(checkbox => {
               if (checkbox.checked) {
                   hasChecked = true;
               }
           });

           if (!hasChecked) {
               alert("선택된 항목이 없습니다.");
               return;
           }

           if (confirm("삭제 하시겠습니까?")) {
               const form = document.getElementById('cart-form');
               form.action = action;
               form.submit();
           }
       }