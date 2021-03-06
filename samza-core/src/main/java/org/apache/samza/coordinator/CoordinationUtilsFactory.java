/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.samza.coordinator;

import org.apache.samza.config.Config;
import org.apache.samza.config.JobCoordinatorConfig;
import org.apache.samza.util.*;


/**
 * factory to instantiate a c{@link CoordinationUtils} service
 */
public interface CoordinationUtilsFactory {

  public static CoordinationUtilsFactory getCoordinationUtilsFactory(Config config) {
    // load the class
    JobCoordinatorConfig jcConfig = new JobCoordinatorConfig(config);
    String coordinationUtilsFactoryClass =   jcConfig.getJobCoordinationUtilsFactoryClassName();

    return ClassLoaderHelper.fromClassName(coordinationUtilsFactoryClass, CoordinationUtilsFactory.class);
  }

  /**
   * get a unique service instance
   * @param groupId - unique id to identify the service
   * @param participantId - a unique id that identifies the participant in the service
   * @param updatedConfig - configs, to define the details of the service
   * @return a unique service instance
   */
  CoordinationUtils getCoordinationUtils(String groupId, String participantId, Config updatedConfig);
}
