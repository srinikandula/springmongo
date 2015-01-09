/*
 * Copyright 2012 Stormpath, Inc. and contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.beakyn.security.model.dao;

import com.beakyn.security.model.User;

/**
 * @author Elder Crisostomo
 */
public interface CustomerDao {

    User getCustomerByUserName(String userName) throws Exception;

    User saveCustomer(User customer) throws Exception;

    User updateCustomer(User customer) throws Exception;
}
