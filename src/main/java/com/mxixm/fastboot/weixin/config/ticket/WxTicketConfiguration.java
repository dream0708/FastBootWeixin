/*
 * Copyright (c) 2016-2018, Guangshan (guangshan1992@qq.com) and the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mxixm.fastboot.weixin.config.ticket;

import com.mxixm.fastboot.weixin.config.WxProperties;
import com.mxixm.fastboot.weixin.service.WxApiService;
import com.mxixm.fastboot.weixin.support.MemoryWxJsTicketStore;
import com.mxixm.fastboot.weixin.support.WxJsTicketManager;
import com.mxixm.fastboot.weixin.support.WxJsTicketStore;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.invoke.MethodHandles;

/**
 * FastBootWeixin WxTicketConfiguration
 *
 * @author Guangshan
 * @date 2018-5-8 00:10:14
 * @since 0.6.0
 */
@Configuration
public class WxTicketConfiguration {

    private static final Log logger = LogFactory.getLog(MethodHandles.lookup().lookupClass());

    private final WxProperties wxProperties;

    public WxTicketConfiguration(WxProperties wxProperties) {
        this.wxProperties = wxProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public WxJsTicketStore wxJsTicketStore() {
        return new MemoryWxJsTicketStore();
    }

    @Bean
    public WxJsTicketManager wxJsTicketManager(WxApiService wxApiService, WxJsTicketStore wxJsTicketStore) {
        return new WxJsTicketManager(this.wxProperties.getAppid(), wxApiService, wxJsTicketStore);
    }

}
