$('button').on({
    click: function(event){
        const eventId = event.currentTarget.parentNode.parentNode.id;
        $.ajax({
            type: "POST",
            url: "/locationHistory.jsp",
            data: {"id":eventId},
            success: function(data){
                $('tr').remove('#'+eventId);
            }
        })
    }
})