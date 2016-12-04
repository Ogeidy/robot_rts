/*globals CURRENT_SIGNALS  */

$(function(){

    setInterval(function() {
        $.ajax({
            url : '/robot/signals',
            type : 'GET',
            cache : false,
            headers : { 'X-Ajax-call' : 'true'},
            success : function() {
                var modelAttr = $("#modelAttr").val();
                console.log('bck: ', modelAttr.bck);
                console.log('bckRight: ', modelAttr.bckRight);
                console.log('bckLeft: ', modelAttr.bckLeft);
                console.log('fwd: ', modelAttr.fwd);
                console.log('fwdRight: ', modelAttr.fwdRight);
                console.log('fwdLeft: ', modelAttr.fwdLeft);
            },
            error : function() {
                console.log('Error while calling ajax');
            }
        });
        },
        1000
    )
});