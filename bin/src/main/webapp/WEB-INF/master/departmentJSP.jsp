<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<head>
    <title id='Description'>This example demostrates how we can manipulate data at client side. The Grid plugin provides you callback functions when you add, remove or update a row.
    </title>
    <link rel="stylesheet" href="<spring:url value="/jqwidgets/styles/jqx.base.css"/>" type="text/css" />
    <script type="text/javascript" src="<spring:url value="/scripts/jquery-1.11.1.min.js"></spring:url>"></script>
    <script type="text/javascript" src="<spring:url value="/jqwidgets/jqxcore.js"></spring:url>"></script>
    <script type="text/javascript" src="<spring:url value="/jqwidgets/jqxdata.js"></spring:url>"></script> 
    <script type="text/javascript" src="<spring:url value="/jqwidgets/jqxbuttons.js"></spring:url>"></script>
    <script type="text/javascript" src="<spring:url value="/jqwidgets/jqxscrollbar.js"></spring:url>"></script>
    <script type="text/javascript" src="<spring:url value="/jqwidgets/jqxmenu.js"></spring:url>"></script>
    <script type="text/javascript" src="<spring:url value="/jqwidgets/jqxcheckbox.js"></spring:url>"></script>
    <script type="text/javascript" src="<spring:url value="/jqwidgets/jqxlistbox.js"></spring:url>"></script>
    <script type="text/javascript" src="<spring:url value="/jqwidgets/jqxdropdownlist.js"></spring:url>"></script>
    <script type="text/javascript" src="<spring:url value="/jqwidgets/jqxgrid.js"></spring:url>"></script>
    <script type="text/javascript" src="<spring:url value="/jqwidgets/jqxgrid.edit.js"></spring:url>"></script>
    <script type="text/javascript" src="<spring:url value="/jqwidgets/jqxgrid.editor.js"></spring:url>"></script>
    <script type="text/javascript" src="<spring:url value="/jqwidgets/jqxgrid.selection.js"></spring:url>"></script> 
    <script type="text/javascript" src="<spring:url value="/scripts/demos.js"></spring:url>"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            // prepare the data
            var data = {};
            var firstNames =
            [
                "Andrew", "Nancy", "Shelley", "Regina", "Yoshi", "Antoni", "Mayumi", "Ian", "Peter", "Lars", "Petra", "Martin", "Sven", "Elio", "Beate", "Cheryl", "Michael", "Guylene"
            ];
            var lastNames =
            [
                "Fuller", "Davolio", "Burke", "Murphy", "Nagase", "Saavedra", "Ohno", "Devling", "Wilson", "Peterson", "Winkler", "Bein", "Petersen", "Rossi", "Vileid", "Saylor", "Bjorn", "Nodier"
            ];
            var productNames =
            [
                "Black Tea", "Green Tea", "Caffe Espresso", "Doubleshot Espresso", "Caffe Latte", "White Chocolate Mocha", "Cramel Latte", "Caffe Americano", "Cappuccino", "Espresso Truffle", "Espresso con Panna", "Peppermint Mocha Twist"
            ];
            var priceValues =
            [
                "2.25", "1.5", "3.0", "3.3", "4.5", "3.6", "3.8", "2.5", "5.0", "1.75", "3.25", "4.0"
            ];
            var generaterow = function (i) {
                var row = {};
                var productindex = Math.floor(Math.random() * productNames.length);
                var price = parseFloat(priceValues[productindex]);
                var quantity = 1 + Math.round(Math.random() * 10);
                row["firstname"] = firstNames[Math.floor(Math.random() * firstNames.length)];
                row["lastname"] = lastNames[Math.floor(Math.random() * lastNames.length)];
                row["productname"] = productNames[productindex];
                row["price"] = price;
                row["quantity"] = quantity;
                row["total"] = price * quantity;
                return row;
            }
            for (var i = 0; i < 10; i++) {
                var row = generaterow(i);
                data[i] = row;
            }
            var source =
            {
                localdata: data,
                datatype: "local",
                datafields:
                [
                    { name: 'firstname', type: 'string' },
                    { name: 'lastname', type: 'string' },
                    { name: 'productname', type: 'string' },
                    { name: 'quantity', type: 'number' },
                    { name: 'price', type: 'number' },
                    { name: 'total', type: 'number' }
                ],
                addrow: function (rowid, rowdata, position, commit) {
                    // synchronize with the server - send insert command
                    // call commit with parameter true if the synchronization with the server is successful 
                    //and with parameter false if the synchronization failed.
                    // you can pass additional argument to the commit callback which represents the new ID if it is generated from a DB.
                    commit(true);
                },
                deleterow: function (rowid, commit) {
                    // synchronize with the server - send delete command
                    // call commit with parameter true if the synchronization with the server is successful 
                    //and with parameter false if the synchronization failed.
                    commit(true);
                },
                updaterow: function (rowid, newdata, commit) {
                    // synchronize with the server - send update command
                    // call commit with parameter true if the synchronization with the server is successful 
                    // and with parameter false if the synchronization failed.
                    commit(true);
                }
            };
            var dataAdapter = new $.jqx.dataAdapter(source);
            // initialize jqxGrid
            $("#jqxgrid").jqxGrid(
            {
                width: 850,
                height: 350,
                source: dataAdapter,
                editable: true,
                showtoolbar: true,
                rendertoolbar: function (toolbar) {
                    var me = this;
                    var container = $("<div style='margin: 5px;'></div>");
                    toolbar.append(container);
                    container.append('<input id="addrowbutton" type="button" value="Add New Row" />');
                    container.append('<input style="margin-left: 5px;" id="addmultiplerowsbutton" type="button" value="Add Multiple New Rows" />');
                    container.append('<input style="margin-left: 5px;" id="deleterowbutton" type="button" value="Delete Selected Row" />');
                    container.append('<input style="margin-left: 5px;" id="updaterowbutton" type="button" value="Update Selected Row" />');
                    $("#addrowbutton").jqxButton();
                    $("#addmultiplerowsbutton").jqxButton();
                    $("#deleterowbutton").jqxButton();
                    $("#updaterowbutton").jqxButton();
                    // update row.
                    $("#updaterowbutton").on('click', function () {
                        var datarow = generaterow();
                        var selectedrowindex = $("#jqxgrid").jqxGrid('getselectedrowindex');
                        var rowscount = $("#jqxgrid").jqxGrid('getdatainformation').rowscount;
                        if (selectedrowindex >= 0 && selectedrowindex < rowscount) {
                            var id = $("#jqxgrid").jqxGrid('getrowid', selectedrowindex);
                            var commit = $("#jqxgrid").jqxGrid('updaterow', id, datarow);
                            $("#jqxgrid").jqxGrid('ensurerowvisible', selectedrowindex);
                        }
                    });
                    // create new row.
                    $("#addrowbutton").on('click', function () {
                        var datarow = generaterow();
                        var commit = $("#jqxgrid").jqxGrid('addrow', null, datarow,"first");
                    });
                    // create new rows.
                    $("#addmultiplerowsbutton").on('click', function () {
                        $("#jqxgrid").jqxGrid('beginupdate');
                        for (var i = 0; i < 10; i++) {
                            var datarow = generaterow();
                            var commit = $("#jqxgrid").jqxGrid('addrow', null, datarow);
                        }
                        $("#jqxgrid").jqxGrid('endupdate');
                    });
                    // delete row.
                    $("#deleterowbutton").on('click', function () {
                        var selectedrowindex = $("#jqxgrid").jqxGrid('getselectedrowindex');
                        var rowscount = $("#jqxgrid").jqxGrid('getdatainformation').rowscount;
                        if (selectedrowindex >= 0 && selectedrowindex < rowscount) {
                            var id = $("#jqxgrid").jqxGrid('getrowid', selectedrowindex);
                            var commit = $("#jqxgrid").jqxGrid('deleterow', id);
                        }
                    });
                },
                columns: [
                  { text: 'First Name', datafield: 'firstname', width: 200,columntype: 'textfield'},
                  { text: 'Last Name', datafield: 'lastname', width: 200 },
                  { text: 'Product', datafield: 'productname', width: 180 },
                  { text: 'Quantity', datafield: 'quantity', width: 80, cellsalign: 'right' },
                  { text: 'Unit Price', datafield: 'price', width: 90, cellsalign: 'right', cellsformat: 'c2' },
                  { text: 'Total', datafield: 'total',  cellsalign: 'right', cellsformat: 'c2' }
                ]
            });    
        });
    </script>
</head>
<body class='default'>
    <div id="jqxgrid">
    </div>
</body>
</html>