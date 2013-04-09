package cmu.ds.mr.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class RedOutputCollector implements OutputCollector<String, Integer> {

  private Map<String, Integer> outlist;

  private String basePath;
  private int taskNum;

  public RedOutputCollector(String basePath, int taskNum) throws IOException {
    this.taskNum = taskNum;
    this.basePath = basePath;
    File file = new File(basePath);
    if(!file.exists())
      file.mkdirs();
    
    outlist = new TreeMap<String, Integer>();
  }

  @Override
  public void collect(String key, Integer value) throws IOException {
    // Assume key is String
    outlist.put(key, value);
  }

  public void writeToDisk() throws IOException {
    String name = String.format("part-%05d", taskNum);
    BufferedWriter bw = new BufferedWriter(
            new FileWriter(basePath + File.pathSeparatorChar + name));
    try {
      for (Entry<String, Integer> en : outlist.entrySet()) {
        bw.write(String.format("%s\t%d\n", en.getKey(), en.getValue()));
      }
    } finally {
      bw.close();
    }
  }

}
