/*globals CURRENT_SIGNALS  */

$(function(){

    setInterval(function() {
        $.ajax({
            url : '/robot/signals',
            type : 'GET',
            cache : false,
            headers : { 'X-Ajax-call' : 'true'},
            success : function() {
                console.log('bck: ', CURRENT_SIGNALS.bck);
                console.log('bckRight: ', CURRENT_SIGNALS.bckRight);
                console.log('bckLeft: ', CURRENT_SIGNALS.bckLeft);
                console.log('fwd: ', CURRENT_SIGNALS.fwd);
                console.log('fwdRight: ', CURRENT_SIGNALS.fwdRight);
                console.log('fwdLeft: ', CURRENT_SIGNALS.fwdLeft);
            },
            error : function() {
                console.log('Error while calling ajax');
            }
        });
        },
        10000
    )
});