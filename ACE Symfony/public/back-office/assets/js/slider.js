$('.slider-hover').attr('data-value', function() {
    var value = $(this).data('value'); // Get the data value of the slider
    return value + ' stars'; // Set the hover text based on the data value
}).hover(function() {
    var value = $(this).val(); // Get the current value of the slider
    $(this).attr('data-value', value ); // Update the data value on hover
}, function() {
    var value = $(this).data('value'); // Get the original data value of the slider
    $(this).attr('data-value', value); // Reset the data value when not hovering
});

