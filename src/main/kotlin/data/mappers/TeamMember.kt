package data.mappers

import core.*
import data.apis.GitHubApi

fun TeamMember.Companion.fromGitHub(
        user: GitHubApi.User,
        repos: List<GitHubApi.UserRepo>,
): TeamMember {
    val skills = repos.mapNotNull { repo ->
        repo.language?.let(TechSkill::fromGitHubLanguage)
    }.distinct()

    return TeamMember(
            name = user.login,
            profileUrl = user.htmlUrl,
            avatarUrl = user.avatarUrl,
            skills = skills,
    )
}