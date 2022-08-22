
<template>
  <div>
  <el-button type="primary" @click="handleAdd" >添加</el-button>
</div>

  <el-table :data="state.tableData" stripe border style="width: 100% "  >
    <el-table-column prop="id" label="ID" width="180" />
    <el-table-column prop="username" label="用户名" width="180" />
    <el-table-column prop="password" label="密码" width="180" />
    <el-table-column prop="sex" label="性别" width="180" />
    <el-table-column prop="email" label="邮箱" width="180" />
    <el-table-column prop="address" label="地址" width="180" />
    <el-table-column label="操作" >
      <template #default="scope">
        <el-button link type="primary" size="small" @click="handleEdit(scope.row)"
        >修改</el-button>
        <el-popconfirm title="确定删除吗?" @confirm="deleteUser(scope.row.id)" confirm-button-text="确定" cancel-button-text="取消">
          <template #reference >
        <el-button link type="danger"  size="small">删除</el-button>
          </template>
        </el-popconfirm>
      </template>
    </el-table-column>
  </el-table>

    <el-dialog v-model="dialogFormVisible " title="添加信息" width="40%">
      <el-form :model="state.form" :rules="state.rules" ref="ruleFormRef">
        <el-form-item label="姓名"  prop="username" >
          <el-input v-model="state.form.username" autocomplete="off" />
        </el-form-item>

        <el-form-item label="密码"  prop="password">
          <el-input v-model="state.form.password" autocomplete="off"  />
        </el-form-item>

        <el-form-item label="邮箱"  prop="email">
          <el-input v-model="state.form.email" autocomplete="off"  placeholder="必须是郑轻大邮箱，@zzuli.edu.cn"/>
        </el-form-item>

        <el-form-item label="性别"  prop="sex">
          <el-radio-group v-model="state.form.sex"  class="ml-4">
            <el-radio label="男" size="small"> 男</el-radio>
            <el-radio label="女" size="small">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="地址"  prop="address">
          <el-input v-model="state.form.address" type="textarea" autocomplete="off"  />
        </el-form-item>
      </el-form>

     <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="save">保存</el-button
        >
      </span>
      </template>
    </el-dialog>

</template>

<script setup>
import {reactive ,ref,getCurrentInstance} from "vue";
import request from "../request.js";
import {ElMessage} from "element-plus";
const { proxy } = getCurrentInstance()
const checkEmail = (rule, value, callback) => {
 if(! /\w[-\w.+]*@zzuli.edu.cn/.test(value)){
   return callback(new Error('请输入正确邮箱地址'))
 }
  callback()
}
const state = reactive ({
  tableData:[],
  form:{},
  rules:{
    username: [
      { required: true, message: '请输入用户名', trigger: 'blur' }
    ],
    password: [
      { required: true, message: '请输入密码', trigger: 'blur' }
    ],
    email: [
      { validator: checkEmail, required: true, trigger: 'blur' }
    ],  address: [
      {  required: true, trigger: 'blur', message: '请输入地址' }
    ],  sex: [
      {  required: true, trigger: 'blur' , message: '请选择性别'}
    ],
  }
})
const load= () =>{
  request.get("/user").then(res =>{
   if (res.code===200){state.tableData = res.data}else {
     ElMessage.error("获取失败")
   }


  })
}
load()

const dialogFormVisible = ref(false)

const handleAdd=() =>{
  dialogFormVisible.value=true
  state.form={}

}
const handleEdit=(row) =>{
  dialogFormVisible.value=true
  state.form=JSON.parse((JSON.stringify(row)))
}

const save=() =>{
  proxy.$refs.ruleFormRef.validate((valid) => {

  if (valid){
    if (state.form.id){
      request.post("/user/editUser",state.form).then(res =>{
        if (res.code===200){ElMessage.success(res.reason)
          dialogFormVisible.value=false
          load()
        }else
          { ElMessage.error(res.reason)
            dialogFormVisible.value=false
            load()}
      })
    }else {

      request.post("/user/addUser",state.form).then(res =>{
       if(res.code===200){ElMessage.success("保存成功")
         dialogFormVisible.value=false
         load()}else {ElMessage.error(res.reason)}

    })
    }}
  })
}

const deleteUser = (id) => {
  request.post("/user/deleteUser/"+id).then(res => {
   if (res.code===200){ ElMessage.success("删除成功")
     dialogFormVisible.value = false
     load()}else {ElMessage.error("删除失败")}

  })
}
</script>