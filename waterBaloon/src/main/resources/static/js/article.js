 /*슬라이드효과*/
 var counter = 1;
 setInterval(function () {
   document.getElementById('radio' + counter).checked = true;
   counter++;
   if (counter > 2) {
     counter = 1;
   }
 }, 4000);