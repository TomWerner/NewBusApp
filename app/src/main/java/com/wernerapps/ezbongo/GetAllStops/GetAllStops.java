/*
 *
 *  * Copyright (C) 2014 Antonio Leiva Gordillo.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.wernerapps.ezbongo.GetAllStops;

import com.wernerapps.ezbongo.DatabaseObjects.Stop;
import com.wernerapps.ezbongo.StopListing.ExpandableSwipeAdapter;

import java.util.List;

public interface GetAllStops {

    public void getStops(OnFinishedListener listener, int groupPos, int childPos);

    public interface OnFinishedListener {

        void onSuccess(List<String> titles, List<List<Stop>> items, int groupPos, int childPos);
        void onFailure(String errorMessage);
    }
}
