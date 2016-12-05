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
                if (data.fwdRight == true) {
                    bumperSelector = '.bumper-rf';
                    bumperCollision(bumperSelector)
                }
                if (data.fwdRight == false) {
                    bumperSelector = '.bumper-rf';
                    bumperNoCollision(bumperSelector)
                }
                if (data.fwdLeft == true) {
                    bumperSelector = '.bumper-lf';
                    bumperCollision(bumperSelector)
                }
                if (data.fwdLeft == false) {
                    bumperSelector = '.bumper-lf';
                    bumperNoCollision(bumperSelector)
                }
                if (data.bckRight == true) {
                    bumperSelector = '.bumper-rb';
                    bumperCollision(bumperSelector)
                }
                if (data.bckRight == false) {
                    bumperSelector = '.bumper-rb';
                    bumperNoCollision(bumperSelector)
                }
                if (data.bckLeft == true) {
                    bumperSelector = '.bumper-lb';
                    bumperCollision(bumperSelector)
                }
                if (data.bckLeft == false) {
                    bumperSelector = '.bumper-lb';
                    bumperNoCollision(bumperSelector)
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