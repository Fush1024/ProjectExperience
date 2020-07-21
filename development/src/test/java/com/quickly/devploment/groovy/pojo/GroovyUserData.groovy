package com.quickly.devploment.groovy.pojo

/**
 * @Author lidengjin* @Date 2020/7/21 5:52 下午
 * @Version 1.0
 */
class GroovyUserData {
    /**
     * 读取特定目录下的文本文件
     * 将每行读取到list中
     */
    def readLineToList(filePath) {
        File file = new File(filePath)
        file.readLines()
    }
}
