<template>

  <div style="margin:0px auto"   >
    <h1  style="margin-left: 120px">登入</h1>
    <el-form
      ref="ruleFormRef"
      :model="ruleForm"
      status-icon
      :rules="rules"
      label-width="120px"
      class="demo-ruleForm"


  >

    <el-form-item label="姓名" prop="username" size="large" >
      <el-input
          v-model="ruleForm.username"
          type="text"
          autocomplete="off"
          placeholder="username"
          :prefix-icon="User"
      />
    </el-form-item>
    <el-form-item label="密码" prop="password" size="large">
      <el-input v-model="ruleForm.password" type="passwordword" autocomplete="off"
                placeholder="password"
                show-password
                :prefix-icon="Key"/>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" style="width: 130px"  @click="loginForm(ruleFormRef) "
      >登入</el-button
      >
      <el-button style="width: 130px" >注册</el-button>
    </el-form-item>
  </el-form>
 </div>
</template>

<script lang="ts" setup>
import {User} from "@element-plus/icons-vue";
import {Key} from "@element-plus/icons-vue";
import { reactive, ref } from 'vue'
import type { FormInstance } from 'element-plus'
import request from "../request.js"

const ruleFormRef = ref<FormInstance>()


const validateusername = (rule: any, value: any, callback: any) => {
  if (value === '') {
    callback(new Error('请输入姓名'))
  } else {
    if (ruleForm.username !== '') {
      if (!ruleFormRef.value) return
      ruleFormRef.value.validateField('username', () => null)
    }
    callback()
  }
}
const validatepassword = (rule: any, value: any, callback: any) => {
  if (value === '') {
    callback(new Error('请输入密码'))
  } 
   else {
    callback()
  }
}

const ruleForm = reactive({
  password: '',
  username: '',

})

const rules = reactive({
  password: [{ validator: validatepassword, trigger: 'blur' }],
  username: [{ validator: validateusername, trigger: 'blur' }],

})

const loginForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.validate((valid) => {
    if (valid) {
      console.log('login!')
      request.post('/user/login').then(res=>{

      })
    } else {
      console.log('error login!')
      return false
    }
  })
}


</script>