/*globals CURRENT_SIGNALS  */

$(function(){

    function bumperCollision(bumperSelector) {
        $(bumperSelector).removeClass('bumper-no-collision');
        $(bumperSelector).addClass('bumper-collision');
    }

    function bumperNoCollision(bumperSelector) {
        $(bumperSelector).removeClass('bumper-collision');
        $(bumperSelector).addClass('bumper-no-collision');
    }

    setInterval(function() {
        var bumperSelector = '';
        $.ajax({
            url : '/robot/signals',
            type : 'GET',
            cache : false,
            headers : { 'X-Ajax-call' : 'true'},
            dataType: 'json',
            success : function(data) {
                if (data.bck == true) {

                }
                if (data.bckRight == true) {

                }
                if (data.bckLeft == true) {

                }
                if (data.fwd == true) {

                }
                if (data.fwdRight == true) {

                }
                if (data.fwdLeft == true) {
                    console.log(data.fwdLeft)
                    bumperSelector = '.bumper-lf';
                    bumperCollision(bumperSelector)
                }
            },
            error : function() {
                console.log('Error while calling ajax');
            }
        });
        },
        1000
    )
});