// 跨域配置
module.exports = {
	publicPath: './',
	// baseUrl: process.env.NODE_ENV === 'production'?'/dist/':'/',
	outputDir: 'dist',
	assetsDir: 'static',
	lintOnSave: false,
    devServer: {                //记住，别写错了devServer//设置本地默认端口  选填
        host: 'localhost',
        port: 8080,
        proxy: {                 //设置代理，必须填
            '/api': {              //设置拦截器  拦截器格式   斜杠+拦截器名字，名字可以自己定
                target: 'http://localhost:8081',     //代理的目标地址
                changeOrigin: true,              //是否设置同源，输入是的
                pathRewrite: {                   //路径重写
                    '^/api': '' // 将前缀 '/api' 转为 '/'                    //选择忽略拦截器里面的单词
                }
            }
        }
    }
}