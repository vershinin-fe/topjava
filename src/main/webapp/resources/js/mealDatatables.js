var ajaxUrl = "ajax/profile/meals/";
var datatableApi;

$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "dateTime"
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
            },
            {
                "defaultContent": "Edit",
                "orderable": false
            },
            {
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ]
    });
    makeEditable();
});

function getFiltered() {
    var form = $("#filterForm");

    $.ajax({
        type: "GET",
        url: ajaxUrl + 'filter',
        data: form.serialize(),
        success: function (data) {
            datatableApi.clear().rows.add(data).draw();
        }
    });
}

function clearFilter() {
    $("#filterForm").find("input").val('');
    updateTable();
}