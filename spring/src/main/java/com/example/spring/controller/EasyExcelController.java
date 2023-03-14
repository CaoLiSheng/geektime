package com.example.spring.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.util.MapUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.example.spring.util.CustomMergeStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/easyexcel")
public class EasyExcelController {

    @GetMapping()
    public String index() {
        // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
        // {} 代表普通变量 {.} 代表是list的变量
        String templateFileName = "xlsx" + File.separator + "test_tpl.xlsx";

        String fileName = "xlsx" + File.separator + "complexFill" + System.currentTimeMillis() + ".xlsx";
        // 方案1
        try (ExcelWriter excelWriter = EasyExcel.write(fileName).withTemplate(templateFileName).build()) {
            List<Map<String, Object>> data = data();
            WriteSheet writeSheet = EasyExcel.writerSheet()
                    .registerWriteHandler(new CustomMergeStrategy(data.stream().map(r -> r.get("ksmc").toString())
                            .collect(Collectors.toList()), 0))
                    .registerWriteHandler(new CustomMergeStrategy(data.stream().map(r -> r.get("myzj").toString())
                            .collect(Collectors.toList()), 8))
                    .build();
            // 这里注意 入参用了forceNewRow 代表在写入list的时候不管list下面有没有空行 都会创建一行，然后下面的数据往后移动。默认 是false，会直接使用下一行，如果没有则创建。
            // forceNewRow 如果设置了true,有个缺点 就是他会把所有的数据都放到内存了，所以慎用
            // 简单的说 如果你的模板有list,且list不是最后一行，下面还有数据需要填充 就必须设置 forceNewRow=true 但是这个就会把所有数据放到内存 会很耗内存
            // 如果数据量大 list不是最后一行 参照下一个
            FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
            excelWriter.fill(data, fillConfig, writeSheet);
            Map<String, Object> map = MapUtils.newHashMap();
            map.put("zjzj", 6);
            map.put("ptzj", 12);
            map.put("zj", 18);
            excelWriter.fill(map, writeSheet);
        }

        return "OK";
    }

    public List<Map<String, Object>> data() {
        List<Map<String, Object>> ret = new ArrayList<>();
        for (int i = 0; i<6; i++) {
            Map<String, Object> row = new HashMap<>();
            row.put("ksmc", "科室" + i/2);
            row.put("type", i%2==0?"专家":"普通");
            row.put("zhuanjia", i%2==0?i/2+1:null);
            row.put("putong", i%2==0?null:(i/2+1) * 2);
            row.put("myzj", (i/2+1) * 3);
            row.put("zjrg", null);
            row.put("zjhj", null);
            row.put("ptrg", null);
            row.put("pthj", null);
            ret.add(row);
        }
        return ret;
    }

}
