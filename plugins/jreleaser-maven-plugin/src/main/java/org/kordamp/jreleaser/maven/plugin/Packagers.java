/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2020-2021 Andres Almiray.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kordamp.jreleaser.maven.plugin;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Andres Almiray
 * @since 0.1.0
 */
public class Packagers extends AbstractDomain {
    private final Brew brew = new Brew();
    private final Chocolatey chocolatey = new Chocolatey();
    private final Scoop scoop = new Scoop();
    private final Snap snap = new Snap();

    void setAll(Packagers packagers) {
        setBrew(packagers.brew);
        setChocolatey(packagers.chocolatey);
        setScoop(packagers.scoop);
        setSnap(packagers.snap);
    }

    public Brew getBrew() {
        return brew;
    }

    public void setBrew(Brew brew) {
        this.brew.setAll(brew);
    }

    public Chocolatey getChocolatey() {
        return chocolatey;
    }

    public void setChocolatey(Chocolatey chocolatey) {
        this.chocolatey.setAll(chocolatey);
    }

    public Scoop getScoop() {
        return scoop;
    }

    public void setScoop(Scoop scoop) {
        this.scoop.setAll(scoop);
    }

    public Snap getSnap() {
        return snap;
    }

    public void setSnap(Snap snap) {
        this.snap.setAll(snap);
    }

    @Override
    public Map<String, Object> asMap() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.putAll(brew.asMap());
        map.putAll(chocolatey.asMap());
        map.putAll(scoop.asMap());
        map.putAll(snap.asMap());
        return map;
    }
}
