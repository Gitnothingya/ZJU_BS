本目录为项目部署目录,使用docker快速部署网站

已测试环境

>Windows 10 专业版  22H2
>

安装最新版本docker, 在当前目录下执行

```shell
docker-compose up -d
```

访问前端网址 http://localhost:5173



**各目录功能**

`./backend/`: 项目后端,为前端提供数据库访问

`./frontend/`: 项目前端, 提供网页访问

`./iotsim/`: 物联网模拟器

`./mosquitto/`: `mqtt broker`

`./mqttsub/`: 项目后端, 连接`mosquitto` 获取信息并存入数据库

`./mysql/`:  数据库

