  // 가격 설정 (예시로 26,500원을 기본 가격으로 설정)
  var originalPrice = 26500;
    
  function calculateTotalPrice() {
      // 선택된 수량 가져오기
      var quantity = document.getElementById("quantity").value;
      // 총 가격 계산
      var totalPrice = originalPrice * quantity;
      // 계산된 가격을 화면에 표시
      document.getElementById("totalPrice").textContent = numberWithCommas(totalPrice) + "원";
  }
  
  function order() {
      alert("주문하기 버튼을 눌렀습니다!");
  }
  
  function addToCart() {
      alert("장바구니 담기 버튼을 눌렀습니다!");
  }
  
  // 숫자에 천 단위 콤마 추가하는 함수
  function numberWithCommas(x) {
      return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
  }