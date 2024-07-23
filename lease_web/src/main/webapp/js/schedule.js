//添加日程信息
function  saveSchedule(){
    var title=prompt("请输入添加的内容");
    var request;
    if(window.XMLHttpRequest){
        request= new XMLHttpRequest();
    }else{
        request= new ActiveXObject("Microsoft.XMLHTTP");
    }
    request.open("get","/schedule/addSchedule?title="+title,true)
    request.send();
    //接收服务器响应的信息
    request.onreadystatechange=function(){
        if(request.readyState==4 && request.status==200){
            // jsonObj ->result对象
             var JsonObj=JSON.parse(request.responseText);
             alert(JsonObj);
             if(JsonObj.code==200){
                   alert("添加成功!");
                   //立刻查询
                 getSchedule(1,2,'');
             }
        }
    }


}

function  removeSchedule(sid){
    if(confirm('是否删除')){
       // location.href="/schedule/removeSchedule?sid="+sid;

        var request;
        if(window.XMLHttpRequest){
            request= new XMLHttpRequest();
        }else{
            request= new ActiveXObject("Microsoft.XMLHTTP");
        }
        request.open("get","/schedule/removeSchedule?sid="+sid,true)
        request.send();
        //接收服务器响应的信息
        request.onreadystatechange=function(){
            if(request.readyState==4 && request.status==200){
                // jsonObj ->result对象
                var JsonObj=JSON.parse(request.responseText);
                if(JsonObj.code==200){
                    alert("删除成功!");
                    //立刻查询
                    getSchedule(1,2,'');
                }
            }
        }

    }else{
        alert("取消删除!");
    }
}

//根据sid查询日程信息(到updateSchedule.jsp展示)
function findScheduleById(sid){
    location.href="/schedule/findScheduleById?sid="+sid;
}







/*-----------------*/
 //搜索条件(功能)
 function  search(){
      //获取搜索条件
      var keyWords=document.getElementById("keyWords").value;
          getSchedule(pageKeyWordsJson.pageNum,pageKeyWordsJson.pageSize,keyWords)
 }




//页面大小发生改变的事件
function  handlePageSize(select){
    // alert(select.value)
    var pageSize=select.value;
    var keyWords= document.getElementById("keyWords").value;
   // location.href="/schedule/getScheduleByUid?pageSize="+pageSize+"&keyWords="+keyWords;

    getSchedule(1,pageSize,keyWords);

}


//json格式保存分页,条件相关的数据
var pageKeyWordsJson={
     pageNum:1,
     pageSize:2,
     keyWords:''
}

//支持异步的分页条件查询
function getSchedule(pageNum,pageSize,keyWords){
           //pageKeyWordsJson 保存数据
    pageKeyWordsJson.pageNum=pageNum;
    pageKeyWordsJson.pageSize=pageSize;
    pageKeyWordsJson.keyWords=keyWords;

    var request;
    if(window.XMLHttpRequest){
        request= new XMLHttpRequest();
    }else{
        request= new ActiveXObject("Microsoft.XMLHTTP");
    }
    request.open("post","/schedule/getScheduleByUid",true)
    request.setRequestHeader("Content-Type","application/json;charset=utf-8")
    //使用send方法发送异步请求
   var jsonStr= JSON.stringify(pageKeyWordsJson);
    request.send(jsonStr);
   //接收服务器响应的信息
    request.onreadystatechange=function(){
        if(request.readyState==4 && request.status==200){
            //{"code":200,"message":"成功","data":{"pageNum":1,"pageSize":2,"keyWords":null,"totalPage":4,"scheduleList":[{"sid":1,"uid":1,"title":"学习css","completed":0},{"sid":2,"uid":1,"title":"学习java","completed":1}]}}
            var jsonObject=JSON.parse(request.responseText);

             if(jsonObject.code==200){
                  //解析日程的信息,展示到table中
               var scheduleList=  jsonObject.data.scheduleList;

               /* `` 是js中的模板字符串*/
               var html=`
                      <tr class="ltr">
                        <th>编号</th>
                        <th>内容</th>
                        <th>进度</th>
                        <th>操作</th>
                    </tr>
               `;
               for (var index in scheduleList){
                       html+=`
                            <tr class="ltr">
                        <td>${parseInt(index)+1}</td>
                        <td>${scheduleList[index].title}</td>
                        <td>${scheduleList[index].completed==0?'未完成':'已完成'}</td>
                        <td class="buttonContainer">
                            <button class="btn1" onclick="removeSchedule(${scheduleList[index].sid})">删除</button>
                           <button class="btn1" onclick="findScheduleById(${scheduleList[index].sid})">修改</button> 
                        
                        </td>
            </tr>
               `;
               }
               html+=`
                   <tr class="ltr buttonContainer" >
                        <td colspan="4">
                            <button class="btn1" onclick="saveSchedule()">新增日程</button>
                        </td>
                
                    </tr>
               `;

               document.getElementById("tab").innerHTML=html;


               /*
               *  处理分页信息
               * */
                var pageNum=jsonObject.data.pageNum;
                var pageSize=jsonObject.data.pageSize;
                var keyWords=jsonObject.data.keyWords;
                var totalPage=jsonObject.data.totalPage;


                var pageA=``;
                 if(pageNum!=1){
                    pageA+=` <a href="javascript:void(0)" onclick="getSchedule(${pageNum-1},${pageSize},'${keyWords}')">&lt;</a> `;
                 }


                 for(var currentPage=1;currentPage<=totalPage;currentPage++){
                     if(currentPage==pageNum){
                         pageA+=`     <a  href="javascript:void(0)" onclick="getSchedule(${currentPage},${pageSize},'${keyWords}')" style="text-decoration: none;color: red">${currentPage}</a> &nbsp;&nbsp;`;
                     }else{

                         pageA+=`     <a  href="javascript:void(0)" onclick="getSchedule(${currentPage},${pageSize},'${keyWords}')" style="text-decoration: none">${currentPage}</a> &nbsp;&nbsp;`;
                     }
                 }





                 if(pageNum!=totalPage){
                     pageA+=` <a href="javascript:void(0)" onclick="getSchedule(${pageNum+1},${pageSize},'${keyWords}')">&gt;</a> `;
                 }


                 document.getElementById("pageDiv").innerHTML=pageA;

             }

        }
    }



}