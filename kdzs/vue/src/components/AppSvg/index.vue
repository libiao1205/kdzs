<template>
    <svg :class="iconStyle" aria-hidden="true">
        <use :xlink:href="iconName"></use>
    </svg>
</template>

<script>
    export default {
        name: "AppSvg",
        props: {
            iconStyle: {
                type: String,
                default: 'icon'
            },
            iconClass: {
                type: String,
            },
            iconText:{
                type: String
            }
        },
        computed: {
            iconName() {
                if (!this.iconText){
                    return `#icon-${this.iconClass}`
                }
                let icon;
                switch (this.iconText) {
                    case 'pdf':
                    case 'xls':
                    case 'ppt':
                    case 'txt ':
                        icon = this.iconText
                        break;
                    case 'xlsx':
                        icon = 'xls'
                        break;
                    case 'doc':
                    case 'docx':
                        icon = 'word'
                        break
                    case 'pptx':
                        icon = 'ppt'
                        break
                    default:
                        icon = 'document'
                }
                return `#icon-${icon}`
            }
        }
    }
</script>

<style scoped>

</style>
