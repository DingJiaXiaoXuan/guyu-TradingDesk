/****   http.js   ****/
// 导入封装好的axios实例
import request from './request'

const http ={
    /**
     * methods: 请求
     * @param url 请求地址
     * @param params 请求参数
     */
    get(url,params){
        const config = {
            method: 'get',
            url:url,
			xhrFields: { 
				withCredentials: true//允许带上cookies
			},
			crossDomain: true
        }
        if(params) config.params = params
        return request(config)
    },
    post(url,params){
        const config = {
            method: 'post',
            url:url,
			xhrFields: {
				withCredentials: true//允许带上cookies
			},
			crossDomain: true
        }
        if(params) config.data = params
        return request(config)
    },
    put(url,params){
        const config = {
            method: 'put',
            url:url
        }
        if(params) config.params = params
        return request(config)
    },
    delete(url,params){
        const config = {
            method: 'delete',
            url:url
        }
        if(params) config.params = params
        return request(config)
    },
	uploadFile(url,form) {
		return request({
			url: url,
			data: form,
			method: 'post'
		})
	}

}
//导出
export default http
