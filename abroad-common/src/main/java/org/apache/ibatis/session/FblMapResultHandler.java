/*
 *    Copyright 2009-2012 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.session;

import java.util.HashMap;
import java.util.Map;

/**
* @ClassName: FblMapResultHandler
* @Description: TODO(实现Map的key value对应)
* @author: mengxr
* @date 2017年4月13日 下午3:34:58
*/
@SuppressWarnings("all") 
public class FblMapResultHandler implements ResultHandler {  
    private final Map mappedResults = new HashMap();  
    @Override  
    public void handleResult(ResultContext context) {  
        Map map = (Map) context.getResultObject();   
        mappedResults.put(map.get("key"), map.get("value"));  // xml 配置里面的property的值，对应的列  
    }  
    public Map getMappedResults() {    
        return mappedResults;    
    }    
}  
