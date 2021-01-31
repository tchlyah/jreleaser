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
package org.kordamp.jreleaser.gradle.plugin.internal.dsl

import groovy.transform.CompileStatic
import org.gradle.api.Action
import org.gradle.api.internal.provider.Providers
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import org.kordamp.jreleaser.gradle.plugin.dsl.Gitea
import org.kordamp.jreleaser.gradle.plugin.dsl.Github
import org.kordamp.jreleaser.gradle.plugin.dsl.Gitlab
import org.kordamp.jreleaser.gradle.plugin.dsl.Release

import javax.inject.Inject

/**
 *
 * @author Andres Almiray
 * @since 0.1.0
 */
@CompileStatic
class ReleaseImpl implements Release {
    final GithubImpl github
    final GitlabImpl gitlab
    final GiteaImpl gitea
    final Property<Boolean> enabled

    @Inject
    ReleaseImpl(ObjectFactory objects) {
        enabled = objects.property(Boolean).convention(Providers.notDefined())
        github = objects.newInstance(GithubImpl, objects)
        gitlab = objects.newInstance(GitlabImpl, objects)
        gitea = objects.newInstance(GiteaImpl, objects)
    }

    @Override
    void github(Action<? super Github> action) {
        action.execute(github)
    }

    @Override
    void gitlab(Action<? super Gitlab> action) {
        action.execute(gitlab)
    }

    @Override
    void gitea(Action<? super Gitea> action) {
        action.execute(gitea)
    }

    org.kordamp.jreleaser.model.Release toModel() {
        org.kordamp.jreleaser.model.Release release = new org.kordamp.jreleaser.model.Release()
        if (enabled.present) release.enabled = enabled.get()
        if (github.set) release.github = github.toModel()
        if (gitlab.set) release.gitlab = gitlab.toModel()
        if (gitea.set) release.gitea = gitea.toModel()
        release
    }
}
