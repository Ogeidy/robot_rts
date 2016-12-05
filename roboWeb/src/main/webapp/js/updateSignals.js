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

    function forwardCollision() {
        $('.iri-forward .iri-no-collision').removeClass('visible');
        $('.iri-forward .iri-no-collision').addClass('invisible');
        $('.iri-forward .iri-collision').removeClass('invisible');
        $('.iri-forward .iri-collision').addClass('visible');
    }

    function forwardNoCollision() {
        $('.iri-forward .iri-no-collision').removeClass('invisible');
        $('.iri-forward .iri-no-collision').addClass('visible');
        $('.iri-forward .iri-collision').removeClass('visible');
        $('.iri-forward .iri-collision').addClass('invisible');
    }

    function backCollision() {
        $('.iri-back .iri-no-collision').removeClass('visible');
        $('.iri-back .iri-no-collision').addClass('invisible');
        $('.iri-back .iri-collision').removeClass('invisible');
        $('.iri-back .iri-collision').addClass('visible');
    }

    function backNoCollision() {
        $('.iri-back .iri-no-collision').removeClass('invisible');
        $('.iri-back .iri-no-collision').addClass('visible');
        $('.iri-back .iri-collision').removeClass('visible');
        $('.iri-back .iri-collision').addClass('invisible');
    }

    setInterval(function() {
        var bumperSelector = '';
        var selector = '';
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
                if (data.fwd == true) {
                    forwardCollision()
                }
                if (data.fwd == false) {
                    forwardNoCollision()
                }
                if (data.bck == true) {
                    backCollision()
                }
                if (data.bck == false) {
                    backNoCollision()
                }
            },
            error : function() {
                console.log('Error while calling ajax');
            }
        });
        },
        10
    )
});