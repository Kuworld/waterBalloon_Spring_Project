/**
 * 
 */

$(document).ready(function() {
    $('#categorySelect').change(function() {
        var categoryId = $(this).val();
        var searchTerm = $('input[name="searchTerm"]').val();
        window.location.href = "/admin/items?categoryId=" + categoryId + "&searchTerm=" + searchTerm;
    });
});
