/*globals CURRENT_SIGNALS  */

$(function(){

    setInterval(function() {
        $.ajax({
            url : '/robot/signals',
            type : 'GET',
            cache : false,
            headers : { 'X-Ajax-call' : 'true'},
            dataType: json,
            success : function(data) {
                console.log('bck: ', data.bck);
                console.log('bckRight: ', data.bckRight);
                console.log('bckLeft: ', data.bckLeft);
                console.log('fwd: ', data.fwd);
                console.log('fwdRight: ', data.fwdRight);
                console.log('fwdLeft: ', data.fwdLeft);
            },
            error : function() {
                console.log('Error while calling ajax');
            }
        });
        },
        1000
    )
});