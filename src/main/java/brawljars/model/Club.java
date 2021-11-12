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
package brawljars.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import javax.annotation.Generated;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Generated("org.mili.generator")
@Data
@EqualsAndHashCode
@ToString
public class Club {

  @SerializedName("badgeId")
  private int badgeId;

  @SerializedName("description")
  private String description;

  @SerializedName("members")
  private List<ClubMember> members;

  @SerializedName("name")
  private String name;

  @SerializedName("requiredTrophies")
  private int requiredTrophies;

  @SerializedName("tag")
  private String tag;

  @SerializedName("trophies")
  private int trophies;

  @SerializedName("type")
  private String type;

}