/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package brawljars.api.intern.clubs;

import java.util.concurrent.Future;
import brawljars.api.Api;
import brawljars.api.intern.clubs.info.ClubRequest;
import brawljars.api.intern.clubs.info.ClubResponse;
import brawljars.api.intern.clubs.member.ClubMembersRequest;
import brawljars.api.intern.clubs.member.ClubMembersResponse;

public interface ClubApi extends Api {

  Future<ClubMembersResponse> findClubMembers(ClubMembersRequest clubMembersRequest);

  Future<ClubResponse> findClub(ClubRequest clubRequest);

}