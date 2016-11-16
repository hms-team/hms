/**
 * Created by Larry on 2016/9/29.
 */
var medicineAmtModule = angular.module("medicineAmtModule",[]);
medicineAmtModule.controller("medicineAmtController",function ($scope, $http) {
    var refresh = function () {
        console.log("refresh the data");
        $http({
            method:"POST",
            url:"/hms/medicineAmt/list",
            data:{page:1,pageSize:20,isPagination:'Y'}
        }).success(function (data, status, headers, config) {
            console.log("success:"+data);
            var jsonResult = angular.fromJson(data);
            $scope.medAmts = jsonResult.data;
            var numPerPage = jsonResult.numPerPage;
            $scope.totalCount = jsonResult.totalCount;
            $scope.totalPage = Math.ceil($scope.totalCount/numPerPage);
            $scope.currentPage = jsonResult.currentPage;
        }).error(function (data, status, headers, config){
            console.log("error:"+data);
        });
    };
    refresh();
    $scope.submit=function () {

        var requestData = {name:$scope.name,type:$scope.type,amountWarehouse:$scope.amountWarehouse,amountStorehouse:$scope.amountStorehouse
            ,unit:$scope.unit,expiryDate:$scope.expiryDate,place:$scope.place,price:$scope.price
        };
        console.log(requestData);
        $http({
            method:"POST",
            url:"/hms/medicineAmt/add",
            data:requestData
        }).success(function (data, status, headers, config) {
            console.log("success:"+data+",rtnCode is "+data["rtnCode"]);
            if("000"==data["rtnCode"]){
                console.log("add Medicine amount success");
                $('#medAmt-add').modal('hide');
            }
        }).error(function (data, status, headers, config) {
            console.log("error:"+data);
        });
    };
    $scope.previous=function(){
        var newPage = $scope.currentPage-1;
        if(newPage <= 0){
            console.log("it's the first page, cannot fetch");
            return;
        }
        $http({
            method:"POST",
            url:"/hms/medicineAmt/list",
            data:{currentPage:newPage,numPerPage:5,isPagination:'Y'}
        }).success(function (data, status, headers, config) {
            console.log("success:"+data);
            var jsonResult = angular.fromJson(data);
            $scope.medAmts = jsonResult.data;
            var numPerPage = jsonResult.numPerPage;
            $scope.totalCount = jsonResult.totalCount;
            $scope.totalPage = Math.ceil($scope.totalCount/numPerPage);
            $scope.currentPage = jsonResult.currentPage;
        }).error(function (data, status, headers, config){
            console.log("error:"+data);
        });
    };
    $scope.next=function () {
        var newPage = $scope.currentPage+1;
        if(newPage > $scope.totalPage){
            console.log("it's the last page, cannot fetch");
            return;
        }
        $http({
            method:"POST",
            url:"/hms/medicineAmt/list",
            data:{currentPage:newPage,numPerPage:5,isPagination:'Y'}
        }).success(function (data, status, headers, config) {
            console.log("success:"+data);
            var jsonResult = angular.fromJson(data);
            $scope.medAmts = jsonResult.data;
            var numPerPage = jsonResult.numPerPage;
            $scope.totalCount = jsonResult.totalCount;
            $scope.totalPage = Math.ceil($scope.totalCount/numPerPage);
            $scope.currentPage = jsonResult.currentPage;
        }).error(function (data, status, headers, config){
            console.log("error:"+data);
        });
    };
    $scope.editMedicineAmt = function (medId) {
        console.log("open the modal and edit medAmt with "+medId);
        $http({
            method:"POST",
            url:"/hms/medicineAmt/getById",
            data:{medId:medId}
        }).success(function (data, status, headers, config) {
            console.log("success:"+data);
            var jsonResult = angular.fromJson(data);
            console.log(jsonResult);
            console.log("opening the model"+$scope.name);
            $scope.up_medId = jsonResult.data.medId;
            $scope.up_name = jsonResult.data.name;
            $scope.up_type = jsonResult.data.type;
            $scope.up_amountWarehouse = jsonResult.data.amountWarehouse;
            $scope.up_amountStorehouse = jsonResult.data.amountStorehouse;
            $scope.up_unit = jsonResult.data.unit;
            $scope.up_expiryDate = jsonResult.data.expiryDate;
            $scope.up_place = jsonResult.data.place;
            $scope.up_price = jsonResult.data.price;
            $('#medAmt-update').modal('show');
        });

    };
    var medAmtAddModalReset = function () {
        console.log("reset the medicine amount modal,name"+$scope.name);
        $scope.name={};
        $scope.type={};
        $scope.amountWarehouse={};
        $scope.amountStorehouse={};
        $scope.unit={};
        $scope.expiryDate = {};
        $scope.place = {};
        $scope.price = {};
    };
    $("#medAmt-add").on("hidden.bs.modal", function($scope) {
        console.log("remove the data in medicine add modal");
        medAmtAddModalReset();
        $(this).find('form')[0].reset();
    });
    var medAmtUpdateModalReset = function () {
        console.log("reset the medicine amount modal,name"+$scope.name);
        $scope.up_medId = {};
        $scope.up_name={};
        $scope.up_type={};
        $scope.up_amountWarehouse={};
        $scope.up_amountStorehouse={};
        $scope.up_unit={};
        $scope.up_expiryDate = {};
        $scope.up_place = {};
        $scope.up_price = {};
    };
    $("#medAmt-update").on("hidden.bs.modal", function($scope) {
        console.log("remove the data in medicine add modal");
        medAmtUpdateModalReset();
        $(this).find('form')[0].reset();
    });
    $scope.medicineAmtupdate = function () {
        var requestData = {medId:$scope.up_medId,name:$scope.up_name,type:$scope.up_type,amountWarehouse:$scope.up_amountWarehouse,amountStorehouse:$scope.up_amountStorehouse
            ,unit:$scope.up_unit,expiryDate:$scope.up_expiryDate,place:$scope.up_place,price:$scope.up_price
        };
        console.log("updating the medicine amount with "+requestData);
        $http({
            url:"/hms/medicineAmt/update",
            method:"POST",
            data:requestData
        }).success(function (data, status, headers, config) {
            console.log("success:"+data+",rtnCode is "+data["rtnCode"]);
            if("000"==data["rtnCode"]){
                console.log("add Medicine amount success");
                $('#medAmt-update').modal('hide');
                refresh();
            }

        }).error(function (data, status, headers, config) {
            console.log("error:"+data);
        });
    };
    $scope.refresh = function () {
        refresh();
    };
    $scope.delMedicineAmtModal = function (medId,name) {
        console.log("show the modal for medicine amount delete"+medId+","+name);
        $scope.del_name = name;
        $scope.del_medId = medId;
        $('#medAmt-del').modal('show');
    };
    var medAmtDelete = function(medId){
        $http({
            url:"/hms/medicineAmt/deleteById",
            method:"POST",
            data:{medId:medId}
        }).success(function (data) {
            console.log("medAmtDelete response data:"+data);
            if("000"==data["rtnCode"]){
                console.log("delete successfully");
                $("#medAmt-del").modal("hide");
                refresh();
            }
        });
    };
    $scope.medicineAmtDel = function () {
        console.log("prepare deleting the medicine with :"+$scope.del_name+","+$scope.del_medId);
        medAmtDelete($scope.del_medId);
    };
    $scope.medicineSearch = function(medName){
        console.log("search the result by medName:"+medName);
        $http({
            method:"POST",
            url:"/hms/medicineAmt/list",
            data:{page:1,pageSize:20,isPagination:'Y',name:medName}
        }).success(function (data, status, headers, config) {
            console.log("success:"+data);
            var jsonResult = angular.fromJson(data);
            $scope.medAmts = jsonResult.data;
            var numPerPage = jsonResult.numPerPage;
            $scope.totalCount = jsonResult.totalCount;
            $scope.totalPage = Math.ceil($scope.totalCount/numPerPage);
            $scope.currentPage = jsonResult.currentPage;
        }).error(function (data, status, headers, config){
            console.log("error:"+data);
        });
    };
    $scope.expToWareHouse = function (medAmt,storeAmt) {
        console.log("preparing transfer "+storeAmt+" drug with id = "+medAmt.medId+" to warehouse at "+ new Date());
        if(isNaN(storeAmt)){
            alert("请输入正确的数量");
            return;
        }
        medAmt.amountWarehouse = parseInt(medAmt.amountWarehouse) + parseInt(storeAmt);
        $http({
            method:"POST",
            url:"/hms/medicineAmt/transfer",
            data:{medId:medAmt.medId,source:"OTHERS",destination:"WARE_HS",amount:storeAmt,date:new Date()}
        }).success(function (data, status, headers, config) {
            console.log("success:"+data);
            var jsonResult = angular.fromJson(data);
            medAmt = jsonResult.data;
        }).error(function (data, status, headers, config){
            console.log("error:"+data);
        });
    };
    $scope.export = function (sd_time,ed_time) {
        console.log("downloading the medicine amount excel file from "+sd_time+" to "+ed_time);
        $('#medAmt-export').modal('hide');
        var url ="/hms/medicineAmt/transferRecordList?sd_time="+sd_time+"&ed_time="+ed_time+"&source=OTHERS&destination=WARE_HS";
        //window.open(url);
        window.location.href = url;

    };
    $scope.exportAll = function () {
        console.log("export all records");
        var url = "/hms/medicineAmt/export";
        window.location.href = url;
    }
});
