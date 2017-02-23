/**
 * Copyright 2015 Palantir Technologies
 *
 * Licensed under the BSD-3 License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://opensource.org/licenses/BSD-3-Clause
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.palantir.atlasdb.table.common;

import java.util.Arrays;

import com.google.common.base.Preconditions;
import com.palantir.atlasdb.keyvalue.api.ColumnSelection;
import com.palantir.atlasdb.keyvalue.api.RangeRequest;

class MutableRange {
    private byte[] startRow;
    private final byte[] endRow;
    private final int batchSize;
    private final ColumnSelection columnSelection;


    public MutableRange(byte[] startRow, byte[] endRow, int batchSize) {
        this(startRow, endRow, batchSize, ColumnSelection.all());
    }

    public MutableRange(byte[] startRow, byte[] endRow, int batchSize, ColumnSelection columnSelection) {
        this.startRow = Preconditions.checkNotNull(startRow);
        this.endRow = Preconditions.checkNotNull(endRow);
        this.batchSize = batchSize;
        this.columnSelection = columnSelection;
    }

    public void setStartRow(byte[] startRow) {
        this.startRow = startRow;
    }

    public RangeRequest getRangeRequest() {
        return RangeRequest.builder().startRowInclusive(startRow).endRowExclusive(endRow).retainColumns(columnSelection).build();
    }

    public int getBatchSize() {
        return batchSize;
    }

    public boolean isComplete() {
        return startRow == null;
    }

    @Override
    public String toString() {
        return "MutableRange{" +
                "startRow=" + Arrays.toString(startRow) +
                ", endRow=" + Arrays.toString(endRow) +
                ", batchSize=" + batchSize +
                ", columnSelection=" + columnSelection +
                '}';
    }
}
