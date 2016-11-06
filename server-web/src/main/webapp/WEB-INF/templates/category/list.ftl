<#assign pageName="栏目管理"/>
<#assign name = RequestParameters.name!'' />

<@override name="buttons">
<a href="${ctx}/category/create" class="btn btn-pink btn-sm">添加</a>
</@override>

<@override name="content">

<div class="col-xs-12">
    <table class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
            <th>ID</th>
            <th>栏目代码</th>
            <th>栏目名称</th>
            <th>是否删除</th>
            <th>栏目排序</th>
            <th>创建时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
            <#if categories?? && categories?size gt 0>
                <#list categories as category>
                    <#include "category-tr.ftl"/>
                </#list>
            <#else>
            <tr>
                <td colspan="20">
                    <div class="empty">暂无查询记录</div>
                </td>
            </tr>
            </#if>
        </tbody>
    </table>
</div>
</@override>

<@override name="script">
<script src="${ctx}/static/app/js/category/list.js"></script>
</@override>

<@extends name="../layout.ftl"/>