$(function(){

	//根据总页数判断，如果小于5页，则显示所有页数，如果大于5页，则显示5页。根据当前点击的页数生成

	//每页显示的数目
	var show_per_page = 6;

	//获取content对象里面，数据的数量
	var number_of_items = $("#cash-table").find("tr").length-1;

	//计算页面显示的总页数
	var pageCount = Math.ceil(number_of_items/show_per_page);

	//隐藏该对象下面的所有子元素
	$('#cash-table').find("tr").css('display', 'none');

	//显示第n（show_per_page）元素
	$('#cash-table').find("tr").slice(0, show_per_page+1).css('display', 'table-row');

	//隐藏域默认值
	$('#start_page').val(0);
	$('#current_page').val(0);
	$('#show_per_page').val(show_per_page);
	$('#end_page').val(pageCount);

	//生成分页按钮
	if(pageCount>5){
		page_icon(1,5,0);
	}else{
		page_icon(1,pageCount,0);
	}

	//点击分页按钮触发
	$("body").on("click","#pageGro li",function(){
		var pageNum = parseInt($(this).html())-1;//获取当前页数

		var page = pageNum +1;//跳转页码数
		var show_per_page = parseInt($('#show_per_page').val());

		//开始
		start_from_this = pageNum * show_per_page+1;
		//结束
		end_on_this = start_from_this + show_per_page;
		//隐藏内容ul的所有子元素，获取特定项目并显示它们
		$('#cash-table').find("tr").css('display', 'none').slice(start_from_this, end_on_this).css('display', 'table-row');
		$('#cash-table').find("tr").eq(0).css('display', 'table-row');

		if(pageCount > 5){
			//显示页面
			pageGroup(page,pageCount);
		}else{
			$(this).addClass("on");
			$(this).siblings("li").removeClass("on");
		}
	});

	//点击上一页触发
	$("#pageGro .pageUp").click(function(){
		var pageNum = parseInt($("#pageGro li.on").html());//获取当前页
		if (pageNum <= 1) {
			var page = pageNum;
		}else{
			var page = pageNum-1;
		}
		var show_per_page = parseInt($('#show_per_page').val());

		//开始
		start_from_this = page * show_per_page - show_per_page+1;
		//结束
		end_on_this = start_from_this + show_per_page;

		//隐藏内容ul的所有子元素，获取特定项目并显示它们
		$('#cash-table').find("tr").css('display', 'none').slice(start_from_this,end_on_this).css('display', 'table-row');
        $('#cash-table').find("tr").eq(0).css('display', 'table-row');

		if(pageCount > 5){
			pageUp(pageNum,pageCount);
		}else{
			var index = $("#pageGro ul li.on").index();//获取当前页
			if(index > 0){
				$("#pageGro li").removeClass("on");//清除所有选中
				$("#pageGro ul li").eq(index-1).addClass("on");//选中上一页
			}
		}
	});
	
	//点击下一页触发
	$("#pageGro .pageDown").click(function(){
		var pageNum = parseInt($("#pageGro li.on").html());//获取当前页
		var page = pageNum;
		if (pageNum===pageCount) {
			page = pageNum-1;
		}
		var show_per_page = parseInt($('#show_per_page').val());
		//开始
		start_from_this = page * show_per_page+1;

		//结束
		end_on_this = start_from_this + show_per_page;

		//隐藏内容ul的所有子元素，获取特定项目并显示它们
		$('#cash-table').find("tr").css('display', 'none').slice(start_from_this, end_on_this).css('display', 'table-row');
        $('#cash-table').find("tr").eq(0).css('display', 'table-row');
		if(pageCount > 5){
			pageDown(pageNum,pageCount);
		}else{
			var index = $("#pageGro ul li.on").index();//获取当前页
			if(index+1 < pageCount){
				$("#pageGro li").removeClass("on");//清除所有选中
				$("#pageGro ul li").eq(index+1).addClass("on");//选中上一页
			}
		}
	});

	//点击首页
	$("body").on("click","#pageGro .pagestart",function(){
		var pageNum = $('#start_page').val();
		//开始
		start_from_this = pageNum * show_per_page+1;
		//结束
		end_on_this = start_from_this + show_per_page;

		//隐藏内容ul的所有子元素，获取特定项目并显示它们
		$('#cash-table').find("tr").css('display', 'none').slice(start_from_this, end_on_this).css('display', 'table-row');
        $('#cash-table').find("tr").eq(0).css('display', 'table-row');
		if (pageCount > 5) {
			//显示页码
			pageGroup(1,pageCount);
		}else{
			var index = $("#pageGro ul li.on").index();//获取当前页
			if(index < pageCount){
				$("#pageGro li").removeClass("on");//清除所有选中
				$("#pageGro ul li:first").addClass("on");
			}
		}
	});

	//点击尾页
	$("body").on("click","#pageGro .pageend",function(){
		var pageNum1 = $('#end_page').val();
		var pagenum = pageNum1-2;
		var page = pageNum1-1;

		//开始
		start_from_this = page * show_per_page+1;
		//结束
		end_on_this = start_from_this + show_per_page;

		//隐藏内容ul的所有子元素，获取特定项目并显示它们
		$('#cash-table').find("tr").css('display', 'none').slice(start_from_this, end_on_this).css('display', 'table-row');
        $('#cash-table').find("tr").eq(0).css('display', 'table-row');

		if (pageCount > 5) {
			//显示页码
			pageGroup(pagenum,pageNum1);
			$("#pageGro ul li:last-child").addClass("on").siblings().removeClass("on");
		}else{
			var index = $("#pageGro ul li.on").index();//获取当前页
			if(index < pageCount){
				$("#pageGro li").removeClass("on");//清除所有选中
				$("#pageGro ul li:last-child").addClass("on");
			}
		}

	});
});

//点击跳转页面
function pageGroup(pageNum,pageCount){
	switch(pageNum){
		case 1:
			page_icon(1,5,0);
		break;
		case 2:
			page_icon(1,5,1);
		break;
		case pageCount-1:
			page_icon(pageCount-4,pageCount,3);
		break;
		case pageCount:
			page_icon(pageCount-4,pageCount,4);
		break;
		default:
			page_icon(pageNum-2,pageNum+2,2);
		break;
	}
}

//根据当前选中页生成页面点击按钮
function page_icon(page,count,eq){
	var ul_html = "";
	for(var i=page; i<=count; i++){
		ul_html += "<li>"+i+"</li>";
	}
	$("#pageGro ul").html(ul_html);
	$("#pageGro ul li").eq(eq).addClass("on");
}

//上一页
function pageUp(pageNum,pageCount){
	switch(pageNum){
		case 1:
		break;
		case 2:
			page_icon(1,5,0);
		break;
		case pageCount-1:
			page_icon(pageCount-4,pageCount,2);
		break;
		case pageCount:
			page_icon(pageCount-4,pageCount,3);
		break;
		default:
			page_icon(pageNum-2,pageNum+2,1);
		break;
	}
}

//下一页
function pageDown(pageNum,pageCount){
	switch(pageNum){
		case 1:
			page_icon(1,5,1);
		break;
		case 2:
			page_icon(1,5,2);
		break;
		case pageCount-1:
			page_icon(pageCount-4,pageCount,4);
		break;
		case pageCount:
		break;
		default:
			page_icon(pageNum-2,pageNum+2,3);
		break;
	}
}